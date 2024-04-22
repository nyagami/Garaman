function openModal(ele){
    const dataText = ele?.getAttribute("data") || "{}";
    const data = JSON.parse(dataText);
    const modal = new bootstrap.Modal('#dataModal');
    const inputs = document.querySelectorAll("#dataModal .form-control");
    for(let i = 0; i < inputs.length; i++){
        const input = inputs.item(i);
        const name = input.name;
        if(name !== "file" && data[name]){
            input.value = data[name];
        }else{
            input.value = "";
        }
    }
    modal.show();
}