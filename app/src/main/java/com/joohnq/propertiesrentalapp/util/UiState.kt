sealed class UiState<out T> {
    data object None: UiState<Nothing>()
    data object Loading: UiState<Nothing>()
    data class Success<out T>(val data: T? = null): UiState<T>()
    data class Failure(val error: String?): UiState<Nothing>()
}
