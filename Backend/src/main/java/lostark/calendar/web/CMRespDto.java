package lostark.calendar.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> {
	private int code; //1(성공), -1(실패)
	private String message;
	private T data;
}
