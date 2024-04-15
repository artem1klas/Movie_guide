package ru.yandex.practicum.moviessearch.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.practicum.moviessearch.domain.models.Person

class PersonsAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    var persons = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(parent)


    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])
    }

}