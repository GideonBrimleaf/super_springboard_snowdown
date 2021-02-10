package super_springboard_snowdown.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("EVENTS")
data class Event (
    @Id
    val id:String?=null,
    val name:String,
    val location:String,
    val prizeMoney:Int,
)