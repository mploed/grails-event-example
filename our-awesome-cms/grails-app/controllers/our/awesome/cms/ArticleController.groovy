package our.awesome.cms

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArticleController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleCount: Article.count()]
    }

    def show(Article article) {
        respond article
    }

    def create() {
        respond new Article(params)
    }

    @Transactional
    def save(Article article) {
        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (article.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond article.errors, view:'create'
            return
        }

        article.save flush:true

        //we are now ending a notification with the article through Reactor's EventBus
        notify 'our.awesome.cms.article_published', article

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*' { respond article, [status: CREATED] }
        }
    }

    def edit(Article article) {
        respond article
    }

    @Transactional
    def update(Article article) {
        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (article.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond article.errors, view:'edit'
            return
        }

        article.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*'{ respond article, [status: OK] }
        }
    }

    @Transactional
    def delete(Article article) {

        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        article.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
