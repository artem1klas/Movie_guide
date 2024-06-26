package ru.yandex.practicum.moviessearch.data

import ru.yandex.practicum.moviessearch.data.dto.NameSearchRequest
import ru.yandex.practicum.moviessearch.data.dto.NameSearchResponse
import ru.yandex.practicum.moviessearch.data.network.RetrofitNetworkClient
import ru.yandex.practicum.moviessearch.domain.api.NamesRepository
import ru.yandex.practicum.moviessearch.domain.models.Person
import ru.yandex.practicum.moviessearch.util.Resource

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {
    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NameSearchRequest(expression))

        return when(response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as NameSearchResponse) {
                    Resource.Success(results.map { Person(
                        id = it.id,
                        name = it.title,
                        description = it.description,
                        protoUrl = it.image
                    ) })
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

}