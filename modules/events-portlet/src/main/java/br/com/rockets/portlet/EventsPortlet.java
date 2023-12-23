package br.com.rockets.portlet;

import br.com.rockets.constants.EventsPortletKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodolfo Oliveira
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Events",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EventsPortletKeys.EVENTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EventsPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			
			long ddmStructureId = 35853;
			List<JournalArticle> eventArticles = JournalArticleLocalServiceUtil.getStructureArticles(ddmStructureId);
			
			ArrayList<JournalArticle> articlesLatestVersions = new ArrayList<JournalArticle>();
			
			List<Map<String,List<DDMFormFieldValue>>> ddmFormFieldValuesReferencesMapList = new ArrayList<Map<String,List<DDMFormFieldValue>>>();
			
			for(JournalArticle eventArticle : eventArticles) {
								
				try {					

					long resourcePrimKey = eventArticle.getResourcePrimKey();
					JournalArticle latestVersion = JournalArticleLocalServiceUtil.getLatestArticle(resourcePrimKey);
					
					if(!articlesLatestVersions.contains(latestVersion)) {
						
						articlesLatestVersions.add(latestVersion);
					}
					
					
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
			
			for(JournalArticle article : articlesLatestVersions) {
				ddmFormFieldValuesReferencesMapList.add((Map<String,List<DDMFormFieldValue>>) article.getDDMFormValues().getDDMFormFieldValuesReferencesMap(true));
			}
			
			
			// O que eu estava tentando fazer era criar um List<Map<String, String>> onde as chaves seriam os fieldReference de cada campo da estrutura e o valor seria o valor de cada campo e já passar esse List para consumir na view. Ao invés de passar essa variável ddmFormFieldValuesReferencesMapList, que é um ListList<Map<String, List<DDMFormFieldValue>>.
			
			// Até consegui usar ddmFormFieldValuesReferencesMapList, mas o código não fica tão fácil de ler e tive dificuldade pra conseguir pegar a URL do campo imagem da estrutura. Não consegui converter a string em JSON como fiz nos ADTs.
			
			// Estava tentando pegar o locale pra usar dentro do getString() do for que vem em seguida
			// Locale locale = ThemeDisplay.getLocale()
			
			for(Map<String,List<DDMFormFieldValue>> ddmFFVRMap :  ddmFormFieldValuesReferencesMapList) {
				for(String key : ddmFFVRMap.keySet()) {
					System.out.println(key + ": " + ddmFFVRMap.get(key).get(0).getValue().getString());
				}
			}
			
			renderRequest.setAttribute("ddmFormFieldValuesReferencesMap", ddmFormFieldValuesReferencesMapList);
							
			super.doView(renderRequest, renderResponse);
			
		} catch (PortletException e) {
			e.printStackTrace();
			
		}
		

	}	
}