<%@page import="it.unisa.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.ItemOrder"%>
<%
if (session == null || session.getAttribute("currentSessionUser") == null) {
%>
<%@ include file="./fragments/header.jsp"%>

<%
} else {
%>
<%@ include file="./fragments/headerlogged.jsp"%>
<%
}
%>


<%
OrderBean order = (OrderBean) session.getAttribute("ordine");
LinkedList<ContentBean> products = (LinkedList<ContentBean>) session.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./style/style.css" rel="stylesheet" type="text/css">
<title>Dettagli</title>

</head>

<body>
	<h2>Dettagli</h2>
	<%
	if (order != null) {
	%>

	<div class=details>
		<table>
			<thead class=details>
				<tr>
					<th>Numero ordine</th>
					<th>Costo totale</th>
					<th>Indirizzo di spedizione</th>
					<th>Data ordine</th>
					<th>Data di spedizione</th>

				</tr>
			</thead>

			<tbody class=details>
				<tr>
					<td><%=order.getNumOrdine()%></td>
					<td><%=String.format("%.2f", order.getCostoTotale())%>
						&euro;</td>
					<td><%=order.getIndirizzoSpedizione()%></td>
					<td><%=order.getDataOrdine()%></td>
					<td><%=order.getDataSpedizione()%></td>
				</tr>
			</tbody>
		</table>

		<br>


	</div>
	<table>
		<thead class=details>
			<tr>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Quantita acquistata</th>
				<th>Iva</th>
			</tr>
		</thead>
		<tbody class=catalogo>
			<%
			if (products != null && products.size() != 0) {
				for (ContentBean bean : products) {
			%>
			<tr>
				<td><%=bean.getNomeArt()%></td>
				<td><%=String.format("%.2f", bean.getPrezzoAcquisto())%>
					&euro;</td>
				<td><%=bean.getNumArtAcq()%></td>
				<td><%=bean.getIva()%>%</td>
			</tr>

			<%
			}
			} else {
			%>
			<tr>
				<td colspan="6">Nessun prodotto registrato</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>




	<%
	}
	%>

</body>
<%@ include file="./fragments/footer.html"%>
</html>
