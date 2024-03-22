-- TODO: 비밀번호를 이대로 넣으면 해쉬화 안돼서 해쉬화 시켜서 넣어주게 코드 수정해야함 / 일단 관리자 계정은 미리 넣어두고 나머지는 회원가입할때 USER 권한 갖게 만들어야 할거 같음
INSERT INTO users (email, password, nickname, role) VALUES ('admin@admin', '1234', 'admin', 'ADMIN');
INSERT INTO users (email, password, nickname, role) VALUES ('email2@email2', 'password2', 'nickname2', 'USER');
INSERT INTO users (email, password, nickname, role) VALUES ('email3@email3', 'password3', 'nickname3', 'USE');

INSERT INTO board (title, content, created_at, modified_at, user_id) VALUES ('제목1', '내용1', now(), now(), 1);
INSERT INTO board (title, content, created_at, modified_at, user_id) VALUES ('제목2', '내용2', now(), now(), 2);
INSERT INTO board (title, content, created_at, modified_at, user_id) VALUES ('제목3', '내용3', now(), now(), 2);
INSERT INTO board (title, content, created_at, modified_at, user_id) VALUES ('제목4', '내용4', now(), now(), 3);

INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글1에 유저2 댓글', now(), now(), 1, 2);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글1에 유저3 댓글', now(), now(), 1, 3);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글2에 유저1 댓글', now(), now(), 2, 1);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글2에 유저1 댓글2', now(), now(), 2, 1);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글2에 유저3 댓글', now(), now(), 2, 3);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글3에 유저1 댓글', now(), now(), 3, 1);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글3에 유저3 댓글', now(), now(), 3, 3);
INSERT INTO comment (content, created_at, modified_at, board_id, user_id) VALUES ('게시글4에 유저1 댓글', now(), now(), 4, 1);
