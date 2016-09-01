import java.util.*;
import java.lang.*;


public class biginteger {
	
	private List<Integer> value;
	private boolean positive;
	
	public biginteger(String val){
		
		value = new ArrayList<>();
		
		for(int i = val.length() - 1; i >= 0; i --){
			
			value.add(val.charAt(i) - '0');	
		}
		
		this.positive = true;
	}
	
	public biginteger(List val){
		this.value = val;
		this.positive = true;
	}
	
	public biginteger(List val, boolean v){
		this.value = val;
		this.positive = v;
	}
	public biginteger add(biginteger val){
		
		List<Integer> result = new ArrayList<>();
		if (value.size() >= val.value.size()){
			
			int len = val.value.size();
			
			int carry = 0;
			for (int i = 0; i < len; i ++){
				
				int c = value.get(i) + val.value.get(i) + carry;
				result.add(c % 10);
				
				carry = c / 10;
			}
			
			for (int i = len; i < value.size(); i++){
				
				int c = value.get(i) + carry;
				result.add(c % 10);
				
				carry = c / 10;
			}
			result.add(carry);
			
		}else{

			int len = value.size();
			
			int carry = 0;
			for (int i = 0; i < len; i ++){

				int c = value.get(i) + val.value.get(i) + carry;
				
				result.add(c % 10);
				
				carry = c / 10;
				
			}
			for (int i = len; i < val.value.size(); i++){
				
				int c = val.value.get(i) + carry;
				
				result.add(c % 10);
				
				carry = c / 10;
			}
			
			result.add(carry);
		}
		biginteger sum = new biginteger(result);
		
		System.out.println(sum);
		return sum; 	
		
	}
	
public biginteger mul(biginteger val){
		
		List<Integer> result = new ArrayList<>();
		
		for (int i = 0; i < value.size() + val.value.size(); i++){
			
			result.add(0);
		}
	
		for (int i = 0; i <this.value.size(); i++){
			
			for (int j = 0; j < val.value.size(); j++){
				
				int c = result.get(i+j) + value.get(i)*val.value.get(j);
				
				result.set(i+j,c);
			}
		}
		
		int carry = 0;
		
		for (int i = 0; i < result.size(); i++){
			
			int c = result.get(i) + carry;
			
			result.set(i, c%10);
			
			carry = c /10;
		}
		
		biginteger ans = new biginteger(result);
		System.out.println(ans);
		return ans;
		
	}


	public biginteger sub(biginteger val){
		List<Integer> result = new ArrayList<>();
		
		
		
		if (isBigger(this,val)){
			
			this.positive = true;
			
			int len = val.value.size();
			
			int carry = 0;
			
			for (int i = 0; i< len; i++){
				
				int c = value.get(i) - val.value.get(i) - carry;
				
				if (c < 0){
					
					result.add(c + 10);
					
					carry = 1;
				}else{
					result.add(c);
					
					carry = 0;
				}
			}
			
			for (int i = len; i < value.size(); i++){
				int c = value.get(i) - carry;
				
				if (c < 0){
					
					result.add(c + 10);
					
					carry = 1;
				}else{
					result.add(c);
					
					carry = 0;
				}
			}
			
		}else{
			
			
			this.positive = false;
			
			int len = value.size();
			
			int carry = 0;
			
			for (int i = 0; i< len; i++){
				
				int c = val.value.get(i) - value.get(i) - carry;
				
				if (c < 0){
					
					result.add(c + 10);
					
					carry = 1;
				}else{
					result.add(c);
					
					carry = 0;
				}
			}
			
			for (int i = len; i < val.value.size(); i++){
				int c = val.value.get(i) - carry;
				
				if (c < 0){
					
					result.add(c + 10);
					
					carry = 1;
				}else{
					result.add(c);
					
					carry = 0;
				}
			}
			biginteger ans = new biginteger(result, false);
			System.out.println(ans);
			return ans;
		}
		
		biginteger ans = new biginteger(result);
		System.out.println(ans);
		return ans;
	}
	
	private boolean isBigger(biginteger v1, biginteger v2){
		
		if (v1.value.size() > v2.value.size()){
			return true;
		}else if(v1.value.size() == v2.value.size()){
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println(v1.value.size());
			for (int i = v1.value.size() - 1; i >= 0 ; i--){
				
				if (v1.value.get(i) > v2.value.get(i)){
					return true;
				}
			}
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
			return false;
		}else{
			return false;
		}
		
	}
    public String toString() {
        List<Integer> v = value;
        StringBuilder builder = new StringBuilder();
        for(int i = v.size() - 1; i > -1; i--) {
            builder.append(String.format("%d", v.get(i)));
        }
        while(builder.length() > 0 && builder.charAt(0) == '0') {
            builder.deleteCharAt(0);
        }
        
        if (builder.length() == 0){
        	builder.insert(0, "0");
        }
        if (!this.positive && (builder.length() == 1 && builder.charAt(0) != '0') ){
        	builder.insert(0,"-");
        }
        
        return builder.toString();
    }
    
    
	public static void main(String args[]){
		
		biginteger a = new biginteger("9999999");
		biginteger b = new biginteger("91007");
		biginteger c = b.sub(a);
		System.out.println(a.sub(b));
		
		
		System.out.println(b.sub(a));
		System.out.println(c.positive);
		System.out.println(c);
		biginteger e = new biginteger("999");
		biginteger d = new biginteger("999");
		System.out.println(e.mul(d));
	}
	
}
