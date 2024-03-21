INSERT INTO users (email, password, nickname) VALUES ('email1', 'password1', 'nickname1');
INSERT INTO users (email, password, nickname) VALUES ('email2', 'password2', 'nickname2');
INSERT INTO users (email, password, nickname) VALUES ('email3', 'password3', 'nickname3');

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
