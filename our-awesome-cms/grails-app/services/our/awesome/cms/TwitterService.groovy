package our.awesome.cms

import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector


@Consumer
class TwitterService {

    @Selector('our.awesome.cms.article_published')
    void postArticleToTwitter(Object eventData) {
        println "Posting the Article to Twitter: " + eventData.toString()
    }

}
