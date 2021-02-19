package super_springboard_snowdown.models

import javax.persistence.*

@Entity @Table (name="events")
data class Event (
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) val id:Long?=null,
    @Column (name="name") val name:String,
    @Column (name="location") val location:String,
    @Column (name="prize_money") val prizeMoney:Int,
    @ManyToMany @JoinTable (
        name = "signups",
        joinColumns = [JoinColumn(name = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "snowboarder_id")]
    ) val snowboarders: MutableList<Snowboarder> = mutableListOf(),

    // The following could also be used as simplified syntax but it means we can't add snowboarders to the list and
    // save it to the database.  We can only refer to what is in the db.  We need the join table defined so we can
    // save snowboarders to events.
    // @ManyToMany (mappedBy = "events") val snowboarders: MutableList<Snowboarder> = mutableListOf(),
)