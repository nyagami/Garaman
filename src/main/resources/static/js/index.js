function openModal(ele){
    const dataText = ele?.getAttribute("data") || "{}";
    const data = JSON.parse(dataText);
    const modal = new bootstrap.Modal('#dataModal');
    const inputs = document.querySelectorAll("#dataModal .form-control");
    for(let i = 0; i < inputs.length; i++){
        const input = inputs.item(i);
        const name = input.name;
        if(name !== "file"){
            if(data[name]){
                input.value = data[name];
            }else{
                if(name != "delete"){
                   input.value = "";
                }else{
                    input.checked = false;
                }
            }
        }
    }
    if(!data["id"]){
        document.querySelector("#delete").disabled = true;
    }else{
        document.querySelector("#delete").disabled = false;
    }
    modal.show();
}