package our.awesome.cms

import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Consumer
class EmailNotificationService {

    @Selector("our.awesome.cms.article_published")
    def notifyBossOfNewArticle(Object eventData) {
        println "Notify Boss by Mail of new Article: " + eventData.toString()
    }
}
