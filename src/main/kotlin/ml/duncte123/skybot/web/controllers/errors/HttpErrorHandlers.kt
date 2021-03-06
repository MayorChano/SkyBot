/*
 * Skybot, a multipurpose discord bot
 *      Copyright (C) 2017 - 2019  Duncan "duncte123" Sterken & Ramid "ramidzkh" Khan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ml.duncte123.skybot.web.controllers.errors

import me.duncte123.botcommons.web.WebUtils
import ml.duncte123.skybot.objects.WebVariables
import org.json.JSONObject
import spark.ModelAndView
import spark.kotlin.RouteHandler
import spark.template.jtwig.JtwigTemplateEngine

object HttpErrorHandlers {

    fun notFound(routeHandler: RouteHandler, engine: JtwigTemplateEngine): Any {
        val request = routeHandler.request
        val response = routeHandler.response

        if (request.headers("Accept") != WebUtils.EncodingType.APPLICATION_JSON.type ||
            response.type() != WebUtils.EncodingType.APPLICATION_JSON.type) {

            return engine.render(ModelAndView(WebVariables()
                .put("title", "404").put("path", request.pathInfo()).map, "errors/404.twig"))
        }

        response.type(WebUtils.EncodingType.APPLICATION_JSON.type)

        return JSONObject()
            .put("status", "failure")
            .put("message", "'${request.pathInfo()}' was not found")
            .put("code", response.status())
    }

    fun internalServerError(routeHandler: RouteHandler): Any {
        val request = routeHandler.request
        val response = routeHandler.response

        if (request.headers("Accept") != WebUtils.EncodingType.APPLICATION_JSON.type ||
            response.type() != WebUtils.EncodingType.APPLICATION_JSON.type) {
            return "<html><body><h1>Internal server error</h1></body></html>"
        }

        response.type(WebUtils.EncodingType.APPLICATION_JSON.type)
        return JSONObject()
            .put("status", "failure")
            .put("message", "Internal server error")
            .put("code", response.status())
    }

}
