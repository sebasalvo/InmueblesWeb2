<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html locale="true">
	
	<head>
		<title>Agregar/Editar Inmueble</title>
		<link href="/inmuebles/css/default.css" type="text/css" rel="stylesheet">
		<script type="text/javascript">
			function cancel(){
				//document.forms[0].action="addressList.do";
				window.location = "addressList.do";
			}
		</script>
	</head>
	
	<body>
			
		<h1>Agregar/Editar Inmueble</h1>
		<div align="right">
			<html:link action="addressList">Volver a la Lista</html:link>&nbsp;
		</div>
		<div align="center">
			<ul>
				<font color='green'>
					<html:messages id="message" message="true">
						<li><%= message %></li>
					</html:messages>
				</font>
			</ul>
		</div>
		<div align="center">
			<html:form action="saveAddress">
				<!-- Campos ocultos -->
				<html:hidden property="id"/>
				<!-- Fin de campos ocultos -->
				<table width="80%" >
					<tr>
						<td align="left"><b>* Calle:</b></td>
						<td>
							<html:text property="street" maxlength="100" size="50" />
						</td>
					</tr>
					<tr>
						<td align="left"><b>* N&uacute;mero:</b></td>
						<td>
							<html:text property="number" maxlength="4" size="1" />
						</td>
					</tr>
					<tr>
						<td align="left">Piso:</td>
						<td>
							<html:text property="floor" maxlength="4" size="1" />
						</td>
					</tr>
					<tr>
						<td align="left">Precio (U$S):</td>
						<td>
							<html:text property="price" maxlength="6" size="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Area (m2):</td>
						<td>
							<html:text property="area" maxlength="4" size="1" />
						</td>
					</tr>
					<tr>
						<td align="left">Dormitorios:</td>
						<td>
							<html:radio property="bedrooms" value="0">Monoambiente</html:radio>
							<html:radio property="bedrooms" value="1">Uno</html:radio>
							<html:radio property="bedrooms" value="2">Dos</html:radio>
							<html:radio property="bedrooms" value="3">Tres</html:radio>
						</td>
					</tr>
					<tr>
						<td align="left">Apto cr&eacute;dito:</td>
						<td>
							<html:checkbox property="credit_readyness" />
						</td>
					</tr>
					<tr>
						<td align="left">Tel&eacute;fono 1:</td>
						<td>
							<html:text property="telephone1" maxlength="20" size="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Tel&eacute;fono 2:</td>
						<td>
							<html:text property="telephone2" maxlength="20" size="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Tel&eacute;fono 3:</td>
						<td>
							<html:text property="telephone3" maxlength="20" size="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Notas/Comentarios:</td>
						<td>
							<html:textarea property="comments" cols="60" rows="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Valoraci&oacute;n:</td>
						<td>
							<html:select property="stars">
								<html:option value="0">Seleccione</html:option>
								<html:option value="1">1 estrella</html:option>
								<html:option value="2">2 estrellas</html:option>
								<html:option value="3">3 estrellas</html:option>
								<html:option value="4">4 estrellas</html:option>
								<html:option value="5">5 estrellas</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left">Fecha de creaci&oacute;n:</td>
						<td>
							<bean:write name="inmuebleActionForm" property="createDate" />
						</td>
					</tr>
					<tr>
						<td align="left">Fecha de &uacute;ltima actualizaci&oacute;n:</td>
						<td>
							<bean:write name="inmuebleActionForm" property="lastUpdateDate" />
						</td>
					</tr>
					
					<tr>
						<td align="center" colspan="2">
							<html:submit value="Guardar"/>
							&nbsp;&nbsp;
							<input type="button" onclick="javascript:cancel();" value="Cancelar"/>
						</td>
					</tr>
				</table>
			</html:form>
		</div>

	</body>
	
</html:html>