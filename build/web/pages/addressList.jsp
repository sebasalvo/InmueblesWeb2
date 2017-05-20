<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html locale="true">
	
	<head>
		<title>Lista de Inmuebles</title>
		<link href="/inmuebles/css/default.css" type="text/css" rel="stylesheet">
		
		<script type="text/javascript">
			function confirmarBorrado(id){
				if(confirm("¿Está seguro que desea borrar el inmueble?")){
					document.forms[0].action="removeAddress.do?id="+ id;
                                        return true;
				}else{
					return false;
				}
			}
		</script>
		
	</head>
	
	<body>
				
		<h1>Lista de Inmuebles</h1>
		&nbsp;(<html:link action="pdf">Listado en versi&oacute;n pdf</html:link>)
		&nbsp;(<html:link action="searchHome">Buscador</html:link>)
		<br>
		
		<div align="center">
			<table width="85%" >
				
				<tr>
					<td align="center" colspan="9">
						<html:link action="newAddress">Agregar Nuevo Inmueble</html:link>
					</td>
				</tr>
				
				<tr>
					<td colspan="9">&nbsp;</td>
				</tr>
			
				<logic:present name="inmuebles">
					
					<tr>
						<th align="left">Calle y N&uacute;mero</th>
						<th align="center">Piso</th>
						<th align="center">Precio (U$S)</th>
						<th align="center">Apto cr&eacute;dito</th>
						<th align="center">Area (m2)</th>
						<th align="center">Dormitorios</th>
						<th align="center">Valoraci&oacute;n</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
							
					<logic:iterate id="inmueble" name="inmuebles">
						<tr>
							<td align="left">
								<html:link action="editAddress" paramId="id" paramName="inmueble" paramProperty="id">
									<bean:write name="inmueble" property="street" />&nbsp;<bean:write name="inmueble" property="number" />
								</html:link>
							</td>
							<td align="center">
								<bean:write name="inmueble" property="floor" />
							</td>
							<td align="center">
								<bean:write name="inmueble" property="price" />
							</td>
							<td align="center">
                                                                <%-- <c:if test='${inmueble.credit_readyness == false}'>No</c:if>  
                                                                <c:if test="${inmueble.credit_readyness == true}">S&iacute;</c:if> --%> 
							</td>
							<td align="center">
								<bean:write name="inmueble" property="area" />
							</td>
							<td align="center">
								<bean:write name="inmueble" property="bedrooms" />
							</td>
							<td align="center">
								<bean:write name="inmueble" property="stars" />
							</td>
							<td align="center">
								<html:link action="removeAddress" paramId="id" paramName="inmueble" paramProperty="id" onclick="return confirmarBorrado(this.value);">
									<html:image src="images/delete.gif"/>
								</html:link>
							</td>
						</tr>
					</logic:iterate>
					
				</logic:present>
				
				<tr>
					<td colspan="9">&nbsp;</td>
				</tr>
				
				<tr>
					<td align="center" colspan="9">
						<html:link action="newAddress">Agregar Nuevo Inmueble</html:link>
					</td>
				</tr>
				
			</table>
		</div>

	</body>
	
</html:html>
