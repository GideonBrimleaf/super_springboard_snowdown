package super_springboard_snowdown.models

import javax.persistence.*

@Entity
@Table(name="events")
data class Event (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null,
    @Column(name="name")
    val name:String,
    @Column(name="location")
    val location:String,
    @Column(name="prize_money")
    val prizeMoney:Int
)