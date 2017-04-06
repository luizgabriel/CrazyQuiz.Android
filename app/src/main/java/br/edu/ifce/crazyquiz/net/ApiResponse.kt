package br.edu.ifce.crazyquiz.net

/**
 * Created by luizg on 05/04/2017.
 */
data class ApiResponse<T>(val data: T, val size: Int)