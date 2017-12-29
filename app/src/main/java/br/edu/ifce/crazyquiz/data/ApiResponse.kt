package br.edu.ifce.crazyquiz.data

data class ApiResponse<T>(val data: T, val size: Int)