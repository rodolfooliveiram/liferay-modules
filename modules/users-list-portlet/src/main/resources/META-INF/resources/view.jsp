<%@ include file="/init.jsp" %>

<liferay-ui:search-container
	delta="2"
	emptyResultsMessage="No results found."
>
	<liferay-ui:search-container-results
		results="${usersList}"
	/>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			keyProperty="userId"
			modelVar="user"
			rowIdProperty="userId"
		>
			<liferay-ui:search-container-column-text
				name="user-id"
				value="${user.getUserId()}"
			/>
			
			<liferay-ui:search-container-column-text
				name="screen-name"
				value="${user.screenName}"
			/>
			
			<liferay-ui:search-container-column-text
				name="name"
				value="${user.getFullName()}"
			/>
			
			<liferay-ui:search-container-column-text
				name="email"
				value="${user.getEmailAddress()}"
			/>	
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>

</liferay-ui:search-container>