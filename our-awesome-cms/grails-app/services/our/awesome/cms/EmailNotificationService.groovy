package our.awesome.cms

import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Consumer
class EmailNotificationService {

    @Selector("our.awesome.cms.article_published")
    def notifyBossOfNewArticle(Object eventData) {
        println "now we will notify our boss about a new Article: " + eventData.toString()
    }
}
