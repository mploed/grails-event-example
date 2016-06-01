package somewhere.publishing.plugin

import grails.transaction.Transactional
import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Consumer
class FacebookPublishingService {

    @Selector("our.awesome.cms.article_published")
    def publishOnFacebook(Object eventData) {
        println "Publish Article On Facebook: " + eventData.toString()


    }
}
