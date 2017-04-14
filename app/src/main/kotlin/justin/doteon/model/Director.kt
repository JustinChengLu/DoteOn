package justin.doteon.model

/**
 * @author justin on 2017/04/13 14:17
 * *
 * @version V1.0
 */
class Director {


    var alt: String? = null
    var avatars: Images? = null
    var name: String? = null
    var id: String? = null

    override fun toString(): String {
        return "Director{" +
                "alt='" + alt + '\'' +
                ", avatars=" + avatars +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}'
    }
}
