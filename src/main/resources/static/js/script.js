function registInout() {
    const form = document.inoutForm;
    const p_seno = form.p_seno.value;
    const p_no = form.p_no.value;
    const i_code = form.i_code.value;
    const p_date = form.p_date.value;

    if(p_seno == "") {
        window.alert("예방접종이력번호가 입력되지 않았습니다!");
        form.p_seno.focus();
        return;
    }
    if(p_no == "") {
            window.alert("고객번호가 입력되지 않았습니다!");
            form.p_no.focus();
            return;
    }
    if(i_code == "") {
            window.alert("백신코드가 입력되지 않았습니다!");
            form.i_code.focus();
            return;
    }
    if(p_date == "") {
            window.alert("접종일자가 입력되지 않았습니다!");
            form.p_date.focus();
            return;
    }
    window.alert("예방접종등록 정보가 정상적으로 등록되었습니다!");
    form.submit();
}
function resetForm(){
    const form = document.inoutForm;
    window.alert("정보를 지우고 처음부터 다시 입력 합니다!");
    form.reset();
    form.p_seno.focus();
}
