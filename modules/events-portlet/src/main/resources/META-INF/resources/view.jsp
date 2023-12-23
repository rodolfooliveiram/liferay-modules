<%@ include file="/init.jsp" %>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %>
<%@ page import="com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>


<%

List<Map<String,List<DDMFormFieldValue>>> ddmFormFieldValuesReferencesMap = (List<Map<String,List<DDMFormFieldValue>>>) request.getAttribute("ddmFormFieldValuesReferencesMap");

%>

<!--
Utilizei <ul> apenas para facilitar na visualização dos dados enquanto tentava acessar os dados que precisava.
Após isso, eu faria toda a parte visual.
-->

<ul>
	<c:forEach items="${ddmFormFieldValuesReferencesMap}" var="ddmFFVReferenceMap">
		
			<li>
				<p>
					<strong>eventTitle: </strong>
					${ddmFFVReferenceMap["eventTitle"][0].getValue().getString(locale)}
				</p>
			</li>
			<li>
				<p>
					<strong>eventSummary: </strong>
					${ddmFFVReferenceMap["eventSummary"][0].getValue().getString(locale)}
				</p>
			</li>
			<li>
				<p>
					<strong>eventThumb: </strong>
					${JSONFactoryUtil.createJSONObject(ddmFFVReferenceMap["eventThumb"][0].getValue().getString(locale)).get("url")}
				</p>
			</li>
			<li>
				<p>
					<strong>eventStartDate: </strong>
					${ddmFFVReferenceMap["eventStartDate"][0].getValue().getString(locale)}
				</p>
			</li>
			<li>
				<p>
					<strong>eventEndDate: </strong>
					${ddmFFVReferenceMap["eventEndDate"][0].getValue().getString(locale)}
				</p>
			</li>
	
			
			<br>			
		
	</c:forEach>
</ul>