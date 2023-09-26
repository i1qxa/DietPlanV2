package dietplan.app.oqvvwe.data.remote.entities

import com.example.edamantestapp.data.remoteData.entities.Next
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val next: Next?=null
)
