package com.nano.data.nav

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.model.IModelFactory
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.StandardDialect
import org.thymeleaf.templatemode.TemplateMode

class NavigationDialect : AbstractProcessorDialect(
    "Navigation Dialect",
    "nav",
    StandardDialect.PROCESSOR_PRECEDENCE
) {
    override fun getProcessors(dialectPrefix: String): Set<IProcessor> {
        return setOf(NavigationElementProcessor(dialectPrefix))
    }
}

class NavigationElementProcessor(dialectPrefix: String) : AbstractElementTagProcessor(
    TemplateMode.HTML,
    dialectPrefix,
    "navigation",
    true,
    null,
    false,
    1000
) {
    override fun doProcess(
        context: ITemplateContext,
        tag: org.thymeleaf.model.IProcessableElementTag,
        structureHandler: IElementTagStructureHandler
    ) {
        val modelFactory: IModelFactory = context.modelFactory
        val model = modelFactory.createModel()

        // Получение атрибутов
        val currentItemName = tag.getAttributeValue("currentItemName") ?: ""

        // Добавление навигационного фрагмента
        model.add(modelFactory.createText("<!-- Navigation -->"))

        val navigation = navigationData

        model.add(modelFactory.createText(
            """
            <div th:fragment="navigation">
            <nav class="mb-16 flex gap-6 text-sm font-medium text-gray-400">
        """.trimIndent()
        ))
        navigation.forEachIndexed { index, entry ->
            val currentItemIndex = navigation.indexOfFirst { it.title == currentItemName }
            model.add(modelFactory.createText(entry.toHtmlElement(index == currentItemIndex)))
        }

        model.add(modelFactory.createText(
            """
            </nav>
            </div>
        """.trimIndent()
        ))

        structureHandler.replaceWith(model, false)
    }
}