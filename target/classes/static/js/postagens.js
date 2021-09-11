/*$(document).ready(function () {
	
	console.log("Testando o js.");	
});*/

// abre o modal de remoção de post
$("[id^=btn-modal-remove-]").on("click", function() {
	var idPost = $(this).val();
    console.log("Testando js: " + idPost);
    
    $("#btn-deletar").val(idPost);
    $("#exampleModal").modal("show");
});

$("#btn-deletar").on("click", function(){
	var idPost = $(this).val();
	
	$.ajax({
    	method: "DELETE",
        url: "/post/remover/" + idPost,
        success: function() {
			console.log("Deletou com sucesso");
			
			$("#card-post-" + idPost).remove();
			
			Msg.success('success message', 1);
			//location.reload();
		}
    });
});