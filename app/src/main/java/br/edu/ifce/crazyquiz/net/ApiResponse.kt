package br.edu.ifce.crazyquiz.net

data class ApiResponse<out T>(val data: T, val size: Int)