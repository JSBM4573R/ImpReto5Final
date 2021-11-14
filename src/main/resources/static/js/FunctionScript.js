//  sidebar
let arrow = document.querySelectorAll(".arrow");
for (var i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e) => {
        let arrowParent = e.target.parentElement.parentElement; //selecting main parent of arrow
        arrowParent.classList.toggle("showMenu");
    });
}
let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

//  cokiee logout
$.ajaxSetup({
    beforeSend : function(xhr, settings) {
      if (settings.type == 'POST' || settings.type == 'PUT'
          || settings.type == 'DELETE') {
        if (!(/^http:.*/.test(settings.url) || /^https:.*/
          .test(settings.url))) {
          // Only send the token to relative URLs i.e. locally.
          xhr.setRequestHeader("X-XSRF-TOKEN",
            Cookies.get('XSRF-TOKEN'));
        }
      }
    }
});

//  /user/name-avatar_url
$.get("/user", function(data){
    $("#user").html(data.name);   
});
$.get("/avatar_url", function(data){
    let imagen ='';
    imagen = `<img class="imagen" src="${data.avatar_url}">`
    $("#avatar_url").html(imagen);  
    $("#avatar_urll").html(imagen);           
});

//  Logout
//Para generar el logout tenemos que configurar el SecurityAdapter.js implementando una funcionalidad Cookie para retornar el token
//Luego configuramos el pom agregando el webjar <js-cookie>
var logout =  function() {
    $.post("/logout", function() {
        $("#user").html('');
        Swal.fire({
            icon: 'success',
            title: 'cerraste Sesion!',
        });
        window.location.href="index.html";
    });
    return true;
}
