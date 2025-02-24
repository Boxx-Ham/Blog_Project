/*
 * 삭제 기능
 * 
 * HTML에서 id를 delete-btn으로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면 fetch() 메서드를 통해 /api/articles/DELETE 요청을 보내는 역할 함
 */

const deleteButton = document.getElementById('delete-btn');


if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        console.log(id);
        fetch(`/api/articles/${id}`, {   
            method: 'DELETE'
        })
        .then(() => {   // then() 메서드 : fetch() 메서드가 잘 완료되면 연이어 실행되는 메서드
            alert('삭제가 완료되었습니다.');    // alert() 메서드 : then() 메서드가 실행되는 시점에 웹 브라우저 화면으로 삭제가 완료되었음을 알리는 팝업을 띄워주는 메서드
            location.replace('/articles');  // location.replace() 메서드 : 실행 시 사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨주는 역할하는 메서드
        });
    });
}