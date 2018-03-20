const xhr = new XMLHttpRequest();


xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		const res = JSON.parse(xhr.response);
		console.log(res);
	}
};

const addBtn = document.querySelector('.addButton');

addBtn.addEventListener('click', function(event) {
	event.preventDefault();
	const tag = document.querySelector('#tagInput').value;
	console.log(tag);
	const bookId = addBtn.dataset.bookId;
	console.log(bookId);
	xhr.open('POST', 'http://localhost:8080/add-tag?bookId=' + bookId + '&addTag='+ tag, true);
	xhr.send();
	
	const tagList = document.querySelector(".tags");
	const deleteButton = document.querySelector(".deleteButton");
	appendElement(tagList, createElement('p', tag));
	appendElement(tagList, createElement('button', deleteButton));
});

const tagDeleteButtonList = document.querySelectorAll('.deleteButton')
tagDeleteButtonList.forEach(function(xButton) {
	addTagDeleteListener(xButton)
});


function addTagDeleteListener (element){
	element.addEventListener('click', () => {
		const bookId = element.dataset.delId;
		const tagId = element.dataset.tagId;
		console.log(bookId);
		console.log(tagId);
		xhr.open('DELETE', 'http://localhost:8080/del-tag?bookId=' + bookId + '&tagId=' + tagId, true);
		xhr.send(); 
		
		element.parentNode.remove();
	});
};

function createElement (elem, textValue) {
	const newElem = document.createElement(elem);
	newElem.innerText = textValue;
	return newElem;
}

function appendElement(parent, child) {
	parent.appendChild(child);
}