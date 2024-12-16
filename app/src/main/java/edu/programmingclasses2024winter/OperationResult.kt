package edu.programmingclasses2024winter

sealed interface OperationResult<T> {
  data class Success<T>(val value: T): OperationResult<T>
  data class Failure<T>(val cause: Throwable): OperationResult<T>
}
