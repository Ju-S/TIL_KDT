import React from 'react';

export function BasicRadioButton() {
    // todo: 기본 라디오 버튼.
    // text와 함께 이루어져 있게 만들기
    // 사용처: 로그인 상태 유지
}

export function BasicButton({text, clickEvent, className}) {
    // todo: 기본 버튼
    // 가운데 text가 들어있는 기초적인 버튼
    // 파라미터로 텍스트, 클릭이벤트, css module 을 받아 설정
    return <button className={className} onClick={clickEvent}>{text}</button>
}