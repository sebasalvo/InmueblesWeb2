<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html locale="true">
	
	<head>
		<title>Buscador de Inmuebles</title>
		<link href="/inmuebles/css/default.css" type="text/css" rel="stylesheet">
	</head>
	
	<body>
			
		<h1>Buscador de Inmuebles</h1>
		<div align="right">
			<html:link action="addressList">Volver a la Lista</html:link>&nbsp;
		</div>
		<div align="center">
			<ul>
				<font color='red'>
					<html:messages id="message" message="true">
						<li><%= message %></li>
					</html:messages>
				</font>
			</ul>
		</div>
		<div align="center">
			<html:form action="searchAddress">
				<table width="60%" >
					<tr>
						<td align="left">Calle:</td>
						<td>
							<html:text property="street" maxlength="100" size="50" />
						</td>
					</tr>
					<tr>
						<td align="left">N&uacute;mero:</td>
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
							<html:text property="price" maxlength="5" size="10" />
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
							<html:text property="bedrooms" maxlength="1" size="1" />
						</td>
					</tr>
					<tr>
						<td align="left">Apto cr&eacute;dito:</td>
						<td>
							<html:checkbox property="credit_readyness" />
						</td>
					</tr>
					<tr>
						<td align="left">Tel&eacute;fono:</td>
						<td>
							<html:text property="telephone" maxlength="20" size="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Notas/Comentarios:</td>
						<td>
							<html:textarea property="comments" cols="40" rows="10" />
						</td>
					</tr>
					<tr>
						<td align="left">Valoraci&oacute;n:</td>
						<td>
							<html:text property="stars" maxlength="1" size="1" />&nbsp;(de 1 a 5)
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<html:submit value="Buscar"/>
							&nbsp;&nbsp;
							<html:reset value="Cancelar"/>
						</td>
					</tr>
				</table>
			</html:form>
		</div>

	</body>
	
</html:html>