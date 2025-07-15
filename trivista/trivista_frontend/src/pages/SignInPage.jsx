import React from 'react';
import {BasicButton} from "../components/Buttons";

function SignInPage() {
    // 로그인은 카카오(추후 네이버, 구글도 추가)API를 이용해서 id, 닉네임을 요청.
    // 없다면 spring에서 user DB 에 삽입.
    const testOnClick = () => {
        alert("버튼 클릭 테스트");
    }

    return(
        <div>
            <BasicButton text="로그인" clickEvent={testOnClick}/>
        </div>
    )
}

export default SignInPage;