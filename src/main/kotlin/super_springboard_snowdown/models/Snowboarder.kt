package super_springboard_snowdown.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("SNOWBOARDERS")
data class Snowboarder(@Id val id: String?, val name: String, val age:Int, val profile:String)