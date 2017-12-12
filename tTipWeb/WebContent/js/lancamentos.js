function listarLancamentos(container) {

	$.getJSON("/tTipWeb/api/lancamentos",		
		function(result) {
		
			var dtRealizado = null;
			var html = "";
		
			$.each(result, function(i, item){

				valor = Number(item.valor).toLocaleString(undefined, {minimumFractionDigits: 2, maximumFractionDigits: 2});
				vlPrevisao = Number(item.vlPrevisao).toLocaleString(undefined, {minimumFractionDigits: 2, maximumFractionDigits: 2});
				vlSaldo = Number(item.vlSaldo).toLocaleString(undefined, {minimumFractionDigits: 2, maximumFractionDigits: 2});
				
				if (dtRealizado != item.dtRealizado) {
					
					dtRealizado = item.dtRealizado;
					
					//null, {style: 'currency'}
					
					html += ("<div class='header-data'>" + (new Date(dtRealizado)).toLocaleDateString() + "</div>");
					html += ("<div class='header-valor header-valor-" + (item.vlPrevisao < 0 ? "negativo" : "positivo") + "'>");
					html += ("<label>Previsao</label><span>" + vlPrevisao + "</span></div>");
					html += ("<div class='header-valor header-valor-" + (item.vlSaldo < 0 ? "negativo" : "positivo") + "'>");
					html += ("<label>Saldo</label><span>" + vlSaldo + "</span></div>");
				}
				
				if (item.sgTipoLancamento == "DEBITO") {
					html += ("<a href='/tTipWeb/html/edicaodebito.html?id=" + item._id + "'>");
					html += ("<div class='frame-debito'>");
				}
				
				else {
					html += ("<a href='/tTipWeb/html/edicaocredito.html?id=" + item._id + "'>");
					html += ("<div class='frame-credito'>");
				}

				// Mostra ou não o ícone "pago"
				if (item.fgRealizado) {
					imgPago = "<img id='imgPago' src='../img/tt-green-check.png'>";					
				}
				
				else {
					imgPago = "";
				}
				
				html += ("<span class='item-descricao'>" + item.descricao + imgPago + "</img></span>");
				html += ("<span class='item-valor'>" + valor + "</span>");
				html += ("</div>");
				html += ("</a>");

			});
			$(container).html(html);
		},
		
		function(error) {
			alert(error);
		});
	
}

function carregarLancamento(id) {

	$.getJSON("/tTipWeb/api/lancamentos/" + id,		
		function(result) {
			fillForm(result);
		});
	
}

function gravarLancamento(json) {
	
	$.ajax({
	    url: "/tTipWeb/api/lancamentos",
	    type: "PUT",
	    data: JSON.stringify(json),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    async: false,
	    success: function(msg) {
	        location.replace("/tTipWeb/html");
	    },
    	error: function(req, msg, error) {
	    	alert("erro: " + error)
	    }
	});
	
}

function excluirLancamento(idLancamento) {
	
	$.ajax({
	    url: "/tTipWeb/api/lancamentos/" + idLancamento,
	    type: "DELETE",
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    async: false,
	    success: function(msg) {
	        location.replace("/tTipWeb/html");
	    },
    	error: function(req, msg, error) {
	    	alert("erro: " + error)
	    }
	});
	
}