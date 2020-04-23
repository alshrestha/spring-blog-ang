
export class Post {

    id: string;
    title: string;
    content: string;
    createdDate: Date;
    lastUpdated: Date;
    createdBy: string;
    comments: Comment[];
}