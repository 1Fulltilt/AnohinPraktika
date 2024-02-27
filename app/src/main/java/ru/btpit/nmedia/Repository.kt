package ru.btpit.nmedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun like(id : Int)
  //  fun repost()
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            1,
            "БТПИТ",
            "В Борисоглебском техникуме промышленных и информационных технологий в преддверии Международного дня родного языка советником директора по воспитанию и по взаимодействию с детскими общественными объединениями Алехиной Светланой Вадимовной совместно с преподавателем литературы Винниченко Виктории Витальевной была организована и проведена акция «Путешествие к Лукоморью». Отрывок из поэмы «Руслан и Людмила» Александра Сергеевича Пушкина «У лукоморья дуб зеленый» на родном языке прочитал студент 1 курса специальности «Технология машиностроения» Ризозода Абдулло Киёмидин. Данная акция способствовала развитию интереса к изучению родного языка, сознательного отношения к нему как явлению культуры; осознанию эстетической ценности родного языка.",
            "20 февраля 2024 года 17:39",
            0,
            0,
            false,
            false,
        ),
        Post(
            2,
            "БТПИТ",
            "В преддверии празднования Дня защитников Отечества активисты военно-патриотического клуба «Соколы России» БТПИТ приняли участие в мероприятии «Диалог на равных».",
            "27 Февраля 2024 в 13:50",
            0,
            0,
            false,
            false,
        ),

    )
    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data
    override fun like(id: Int) {

        /*if (post.likedByMe)
            post.amountlike--
        else
            post.amountlike++

        post.likedByMe = !post.likedByMe
        data.value = post
         */
         posts = posts.map{
             if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
         }
        data.value = posts
    }
   /* override fun repost(){
        if (post.repostByMe)
            post.amountrepost--
        else
            post.amountrepost++
        post.repostByMe = !post.repostByMe
        data.value = post
    }*/
}

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like(id: Int) = repository.like(id)
    //fun repost() = repository.repost()
}