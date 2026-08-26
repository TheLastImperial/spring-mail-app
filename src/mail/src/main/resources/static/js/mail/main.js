function openDeleteMail(ele) {
    const eleName = document.getElementById('deleteName');
    const eleDelete = document.getElementById('linkDelete');

    const mailId = ele.getAttribute('template-name');
    eleName.textContent = mailId;
    const urlDelete = `/mails/delete/${mailId}`;
    eleDelete.setAttribute('href', urlDelete)
    const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
    deleteModal.show();
}
