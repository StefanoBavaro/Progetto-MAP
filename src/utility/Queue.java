package utility;

public class Queue {
		private Record begin = null;
		private Record end = null;
		
		private class Record {
	 		 Object elem;
	 		 Record next;
			 Record(Object e) {
				this.elem = e; 
				this.next = null;
			}
		}
		
		 public boolean isEmpty() {
			return this.begin == null;
		}

		 public void enqueue(Object e) {
			if (this.isEmpty())
				this.begin = this.end = new Record(e);
			else { this.end.next = new Record(e);
				this.end = this.end.next;
			}
		}


		public Object first() throws EmptyQueueException {
			if(this.begin == null){ //o usare il metodo isEmpty?
				throw new EmptyQueueException("La coda è vuota.");
			}
			return this.begin.elem;
		}

		 public void dequeue() throws EmptyQueueException {
			if(this.begin == this.end) {
				if(this.begin == null){
					throw new EmptyQueueException("La coda è vuota.");
				}
				else
					this.begin=this.end=null;
			}
			else{
				begin = begin.next;
			}
		}

	}