package super_springboard_snowdown.models

import javax.persistence.*

@Entity @Table (name="snowboarders")
data class Snowboarder (
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) val id:Long?=null,
    @Column (name="name") val name: String,
    @Column (name="age") val age: Int,
    @Column (name="profile") val profile: String,
    @ManyToMany @JoinTable (
        name = "signups",
        joinColumns = [JoinColumn(name = "snowboarder_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    ) val events: MutableList<Event> = mutableListOf(),
)