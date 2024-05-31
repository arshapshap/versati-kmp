package core.utils

fun String.encrypt(): String {
    // TODO: сделать что-то понадёжнее
    return this.hashCode().toString()
}