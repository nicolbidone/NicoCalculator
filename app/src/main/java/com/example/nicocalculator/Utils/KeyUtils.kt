package com.example.nicocalculator.Utils

data class KeyUtils(private var operation: String) {

    companion object {
        private const val LEVEL_ZERO = 0
        private const val LEVEL_ONE = 1
        private const val LEVEL_TWO = 2
    }

    private var precendence = 0

    init {
        precendence = precedenceVal(operation)
    }

    fun getValue(): String {
        return operation
    }

    fun getInt(): Int {
        return operation.toInt()
    }

    override fun toString(): String {
        return operation
    }

    fun getPrecendence(): Int {
        return precendence
    }

    private fun precedenceVal(string: String): Int {
        return when (string) {
            MULTIPLY_OPERATOR -> LEVEL_TWO
            DIVIDE_OPERATOR -> LEVEL_TWO
            ADD_OPERATOR -> LEVEL_ONE
            SUBTRACT_OPERATOR -> LEVEL_ONE
            else -> LEVEL_ZERO
        }
    }
}