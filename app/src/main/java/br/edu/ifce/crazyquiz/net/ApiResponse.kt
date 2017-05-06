package br.edu.ifce.crazyquiz.net

data class ApiResponse<T>(val data: T, val size: Int)