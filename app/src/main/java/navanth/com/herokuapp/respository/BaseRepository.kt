package navanth.com.herokuapp.respository

import navanth.com.herokuapp.model.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T?>?, error: String): T? {
        val result = fetchAPIResponse(call, error)
        var output: T? = null
        when (result) {
            is Output.Success<T> ->
                output = result.output
            is Output.Error -> result.exception
        }
        return output

    }

    private suspend fun <T : Any> fetchAPIResponse(
        call: suspend () -> Response<T?>?,
        error: String
    ): Output<T> {
        val response = call.invoke()
        return if (response?.isSuccessful == true)
            Output.Success(response.body())
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }
}