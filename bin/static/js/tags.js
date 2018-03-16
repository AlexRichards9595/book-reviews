const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		const res = JSON.parse(xhr.response);

		if (res.length) {
			res.forEach(function(state) {
				appendOneElementToBody(state);
			});
		} else {
			appendOneElementToBody(res);
		}
		const appendOneElementToBody = (res) => {
			const body = document.body;

			const tagContainer = document.createElement('div');
			tagContainer.classList.add('tagContainer');

			appendElement(tagContainer, createElement('div', res.tags));
			
			appendElement(body, tagContainer);
		};
		
		const createElement = (elem, textValue) => {
			const newElem = document.createElement(elem);
			newElem.innerText = textValue;
			return newElem;
		};
		
		const appendElement = (parent, child) => {
			parent.appendChild(child);
		};
		/*
		const showAllPropsInObject = () => {
			for (const prop in res) {
				console.log(`${prop}: ${res[prop]}`);
			}
		}
		showAllPropsInObject();
    */
		console.log(res);
	}

};
xhr.open('GET', 'http://localhost:8080/book', true);
xhr.send();