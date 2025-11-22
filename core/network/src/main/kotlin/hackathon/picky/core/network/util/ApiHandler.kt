package hackathon.picky.core.network.util


import android.util.Log
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.ErrorResponse
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> runRemote(block: suspend() -> Response<T>): ApiResponse<T> {
    return try {
        val response = block()
        val errorBodyString = response.errorBody()?.string().orEmpty()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResponse.Success(body)
            } else {

                Log.e("RetrofitError", "${response.code()} : ${response.message()}")

                ApiResponse.Failure.HttpError(
                    code = response.code(),
                    message = "응답 본문이 비어 있습니다.",
                    body = errorBodyString
                )
            }
        } else {
            val errorMessage = try{
                val parsed = Json.decodeFromString<ErrorResponse>(errorBodyString)
                parsed.message
            } catch (e: SerializationException) {
                "서버 오류"
            }

            Log.e("RetrofitError", "${response.code()} : ${response.message()}")

            ApiResponse.Failure.HttpError(
                code = response.code(),
                message = errorMessage,
                body = errorBodyString
            )
        }
    } catch (e: SocketTimeoutException) {
        Log.e("RetrofitError", "Timeout: $e")
        ApiResponse.Failure.NetworkError(e)
    } catch (e: IOException) {
        Log.e("RetrofitError", "Network Error: $e")
        ApiResponse.Failure.NetworkError(e)
    } catch (e: Exception) {
        Log.e("RetrofitError", "Unknown Error: $e")
        ApiResponse.Failure.UnknownApiError(e)
    }
}