package navanth.com.herokuapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel() {
    //create a new Job
    protected val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    protected val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    protected val coroutineScope = CoroutineScope(coroutineContext)
}