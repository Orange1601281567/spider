package spider;

public class RedisTest {
	public static void main(String[] args) throws Exception {
		 Server server = new Server();
	        server.copyTo();
	        System.out.println("CPU使用詳情:"+server.getCpu());
	        System.out.println("核心数:"+server.getCpu().getCpuNum());
	        System.out.println("用户使用率:"+server.getCpu().getUsed());
	        System.out.println("系统使用率:"+server.getCpu().getSys());
	        System.out.println("当前空闲率:"+server.getCpu().getFree());
	}
	
}
